const express = require("express");
const app = express();
require("dotenv").config();
const bodyParser = require("body-parser");
const fs = require("fs");
const path = require("path");

const sequelize = require("./config/sequelize-connection.js");
const stockRouters = require("./routes/stocks-routes.js");
const deliveryStaffsRouters = require("./routes/delivery-staffs-routes.js");
const feedback_delivery_staffsRouters = require("./routes/feedback-delivery-staffs-routes.js");
const log = console.log;


app.use(bodyParser.json());
app.use("/api/stocks", stockRouters); // djebby
app.use("/api/delivery-staffs" ,deliveryStaffsRouters);//Aouichi
app.use("/api/feedback_delivery_staff" ,feedback_delivery_staffsRouters);//Aouichi
sequelize
  .authenticate()
  .then((success) => {
    log("Successfully connected to the database");
    const PORT = process.env.PORT || 3000;
    app.listen(PORT, () => log(`application is running at port ${PORT}`));
  })
  .catch((error) => log(error.message));
