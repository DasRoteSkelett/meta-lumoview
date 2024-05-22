FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

# additional dependencies required to run RAUC on the target
RDEPENDS:${PN}:append = " \
    grub-editenv \
    e2fsprogs-mke2fs \
"
