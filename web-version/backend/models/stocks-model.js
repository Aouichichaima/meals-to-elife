const { DataTypes } = require("sequelize");
const sequelize = require("./sequelize-connection.js");

const Stock = sequelize.define(
    "stocks",
    {
        id: {
            type: DataTypes.INTEGER,
            allowNull: false,
            primaryKey: true,
            autoIncrement: true
        },
        title: {
            type: DataTypes.STRING,
            allowNull: false
        },
        type_stock: {
            type: DataTypes.STRING
        },
        description: {
            type: DataTypes.STRING
        }
    }
);
sequelize.sync();

module.exports = Stock;