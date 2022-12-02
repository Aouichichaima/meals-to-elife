'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class CustomerOrder extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      // define association here
      CustomerOrder.belongsTo(models.User);
      CustomerOrder.belongsTo(models.Restaurant);
    }
  }
  CustomerOrder.init({
    meals: DataTypes.JSON,
    ordered_at: DataTypes.DATE,
    UserId: DataTypes.INTEGER,
    RestaurantId: DataTypes.INTEGER
  }, {
    sequelize,
    modelName: 'CustomerOrder',
  });
  return CustomerOrder;
};