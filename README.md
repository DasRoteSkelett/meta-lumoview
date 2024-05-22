# meta-lumoview

This is a meta-layer that basically has a tiny image which includes [random-mqtt-sender](https://github.com/DasRoteSkelett/random-mqtt-sender), a program that will basically publish MQTT QoS Level 2 messages for testing purposes.

### How to build

This layer uses [kas](https://github.com/siemens/kas) for building a yocto image. So first, you have to install kas, which may require different steps depending on your platform, but this should usually work:

1. Create a python environment `python -m venv /path/to/venv`
1. Source the environment `source /path/to/venv/bin/activate`
1. Install kas `pip install kas`

Then, I would recommend to set `KAS_WORK_DIR` environmental variable to a location outside of this repo. You will need some space (70GB+).

1. Export KAS_WORK_DIR: `export KAS_WORK_DIR=/some/place/with/lots/space`
1. Make sure, directory exists: `mkdir -p ${KAS_WORK_DIR}`
1. From within the kas virtual environment, start the build: `kas build kas-configs/lumoview.yml:kas-configs/limit-cpu-usage.yml`
1. Have a tea. And a coffee. And then another tea. And lunch.

When the build is finished, you can start the virtual machine with
```
cd ${KAS_WORK_DIR}/poky
source oe-init-build-env ../build
runqemu wic
```
You can login with user root (no password).

## To Test the Application

Start a mosquitto MQTT Broker on your machine:
```
docker run -it -d --rm --name mos1 -p 1883:1883 -v ./mosquitto.conf:/mosquitto/config/mosquitto.conf eclipse-mosquitto:2
```
on the virtual machine (you can log into `ssh root@192.168.7.2` if you like) start the `random-mqtt-sender`. The application will print stuff on the console. If everything worked, it will be
```
Published message no xyz...
```

You can subscribe to the messages with `mosquitto_sub -t TestTopic -q 2 -c -i mos_sub -k 3600`

Switch to another console with `Alt-<Cursor-Left>`. Type `networkctl down eth0` to simulate a disruption in the internet connection. You can watch the directory `/tmp/mqtt-persist/` for messages being saved. You can restore network connectivity with `networkctl up eth0`. You should immediately see the missing messages on the mosquitto_sub thread.

#### Know issues

If you pause the network connection for too long, the connection might not get picked up again. Further research needed here, but generally, this should work.

### Update

Build the update package after sourcing the environment (i.e. `cd ${KAS_WORK_DIR}/poky;source oe-init-build-env ../build`) with `bitbake lv-core-image-bundle. Start the qemu (`runqemu wic`). Watch that your grub shows booting from partition A. Log into the machine and type `rauc status`. Copy the file to the machine with executing this on the host.
```
cd ${KAS_WORK_DIR}/build
scp tmp/deploy/images/myqemux86-64/lv-core-image-bundle-myqemux86-64.raucb root@192.168.7.2:/tmp
```

On the target, run `rauc install /tmp/lv-core-image-bundle-myqemux86-64.raucb`. You should be seeing something like Installing ... succeeded. You can type `reboot` now. You will notice, that grub is now booting from the other partition. Also `rauc status` should represent a different state. If you are okay with this, you can mark it as good with `rauc status mark-good`.

For more details, see [rauc documentation](https://rauc.io/)