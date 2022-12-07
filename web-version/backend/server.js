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

const productsRouters = require("./routes/products-routes.js");
const restaurantRouters = require("./routes/restaurants-routes.js");
const usersRouters = require('./routes/users-routes.js');

const log = console.log;


app.use(bodyParser.json());
app.use((req, res, next) => {
  res.setHeader("Access-Control-Allow-Origin", "*");
  res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
  res.setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, DELETE");
  next();
});
app.use("/api/stocks", stockRouters); // djebby

app.use("/api/delivery-staffs" ,deliveryStaffsRouters);//Aouichi
app.use("/api/feedback_delivery_staff" ,feedback_delivery_staffsRouters);//Aouichi

app.use("/api/products", productsRouters); // djebby
app.use("/api/restaurants", restaurantRouters); // ichrak

app.use('/api/users', usersRouters); // Oumaima

sequelize
  .authenticate()
  .then((success) => {
    log("Successfully connected to the database");
    const PORT = process.env.PORT || 3000;
    app.listen(PORT, () => log(`application is running at port ${PORT}`));
  })
  .catch((error) => log(error.message));
