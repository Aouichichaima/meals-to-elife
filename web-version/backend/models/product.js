'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Product extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      // define association here
      Product.belongsTo(models.Stock);
    }
  }
  Product.init({
    name: DataTypes.STRING(99),
    price: DataTypes.DECIMAL(13, 3),
    quantity: DataTypes.INTEGER,
    description: DataTypes.STRING(999),
    stockId: DataTypes.INTEGER
  }, {
    sequelize,
    modelName: 'Product',
  });
  return Product;
};