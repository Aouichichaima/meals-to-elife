const express = require("express");
const app = express();
require("dotenv").config();
const bodyParser = require("body-parser");
const fs = require("fs");
const path = require("path");

const sequelize = require("./config/sequelize-connection.js");
const stockRouters = require("./routes/stocks-routes.js");
const restaurantRouters = require("./routes/restaurants-routes.js");
const customerorderRouters = require("./routes/customer-orders-routes.js");
const log = console.log;


app.use(bodyParser.json());
app.use("/api/stocks", stockRouters); // djebby
app.use("/api/restaurants", restaurantRouters); // ichrak
app.use("/api/customer-orders", customerorderRouters); //ichrak

sequelize
  .authenticate()
  .then((success) => {
    log("Successfully connected to the database");
    const PORT = process.env.PORT || 3000;
    app.listen(PORT, () => log(`application is running at port ${PORT}`));
  })
  .catch((error) => log(error.message));
