const Sequelize = require("sequelize");
const sequelize = new Sequelize(
  "meals_to_elife_dev_db",
  "root",
  "toor",
  { host: "127.0.0.1", dialect: "mysql" }
);

module.exports = sequelize;