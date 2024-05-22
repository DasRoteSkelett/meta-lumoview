inherit bundle

LICENSE = "CLOSED"

RAUC_BUNDLE_COMPATIBLE = "myqemux86-64"

RAUC_BUNDLE_FORMAT = "verity"

RAUC_BUNDLE_SLOTS = "efi rootfs"

RAUC_IMAGE_FSTYPE = "tar.bz2"

RAUC_SLOT_rootfs = "lv-core-image"

RAUC_SLOT_efi = "boot-image"
RAUC_SLOT_efi[file] = "efi-boot.vfat"
RAUC_SLOT_efi[type] = "boot"

RAUC_KEY_FILE = "${THISDIR}/../files/development-1.key.pem"
RAUC_CERT_FILE = "${THISDIR}/../files/development-1.cert.pem"
