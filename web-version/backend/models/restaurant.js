'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Restaurant extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      // define association here
      Restaurant.belongsTo(models.User);
      Restaurant.hasMany(models.Stock);
    }
  }
  Restaurant.init({
    name: DataTypes.STRING(45),
    address: DataTypes.STRING(250),
    menu: DataTypes.JSON,
    rating: DataTypes.DECIMAL(2, 1),
    UserId: DataTypes.INTEGER
  }, {
    sequelize,
    modelName: 'Restaurant',
  });
  return Restaurant;
};