FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append = " \
    file://60-eth0.network \
"

do_install:append() {
    install -m 0644 ${WORKDIR}/60-eth0.network ${D}${systemd_unitdir}/network/
}
