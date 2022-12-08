const bcrypt = require("bcryptjs");

module.exports = (
  isPassCrypted = false,
  condidatePassword,
  dbSavedPassword,
  salt = 10
) =>
  isPassCrypted
    ? bcrypt.compareSync(condidatePassword, dbSavedPassword)
    : condidatePassword === dbSavedPassword;
