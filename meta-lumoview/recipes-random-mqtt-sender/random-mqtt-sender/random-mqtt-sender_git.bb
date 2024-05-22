LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d97db349985d1c70c9b899e31eb7ac28"

SRC_URI = "git://github.com/DasRoteSkelett/random-mqtt-sender.git;protocol=https;branch=main"


PV = "1.0+git"
SRCREV = "3e2e3fd98935f6253b54848895df84aa44594e56"

S = "${WORKDIR}/git"

DEPENDS = "paho-mqtt-cpp"

inherit cmake


