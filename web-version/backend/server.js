const express = require("express");
const Sequelize = require("sequelize");
const app = express();
require("dotenv").config();
const bodyParser = require("body-parser");
const fs = require("fs");
const path = require("path");
const log = console.log;

app.use(bodyParser.json());

const sequelize = new Sequelize(
  process.env.DATABASE_NAME,
  process.env.DATABASE_USER,
  process.env.DATABASE_PASS,
  { host: process.env.DATABASE_HOST, dialect: "mysql" }
);

sequelize
  .authenticate()
  .then((success) => {
    log("Successfully connected to the database");
    const PORT = process.env.PORT || 3000;
    app.listen(PORT, () => log(`Application is running at port ${PORT}`));
  })
  .catch((error) => log(error.message));
