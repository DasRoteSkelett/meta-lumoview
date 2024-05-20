SUMMARY = "Lumoview example tast initial core image"
DESCRIPTION = "A very simple image used for the IoT logging task"

inherit core-image

IMAGE_LINGUAS = "en-us"

IMAGE_INSTALL = " \
    packagegroup-core-full-cmdline \
    packagegroup-core-base-utils \
    random-mqtt-sender \
    nmap \
"

IMAGE_FSTYPES = " \
    ext4 \
"

IMAGE_FEATURES = " \
    debug-tweaks \
    ssh-server-openssh \
    tools-debug \
"
