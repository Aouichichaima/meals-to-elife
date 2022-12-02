'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Stock extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      // define association here
      Stock.hasMany(models.Product);
      // Stock.belongsTo(models.Restaurant);
    }
  }
  Stock.init({
    title: DataTypes.STRING(50),
    type_stock: DataTypes.STRING(99),
    description: DataTypes.STRING(999),
    RestaurantId: DataTypes.INTEGER
  }, {
    sequelize,
    modelName: 'Stock',
  });
  return Stock;
};