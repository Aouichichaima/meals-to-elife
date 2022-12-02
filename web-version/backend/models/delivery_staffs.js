'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class DeliveryStaff extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      // define association here
      DeliveryStaff.belongsTo(models.Restaurant);
      DeliveryStaff.hasMany(models.FeedbackDeliveryStaff);
    }
  }
  DeliveryStaff.init({
    cin: DataTypes.STRING(8),
    phone: DataTypes.STRING(30),
    image_path: DataTypes.STRING(999),
    first_name: DataTypes.STRING(45),
    last_name: DataTypes.STRING(45),
    RestaurantId: DataTypes.INTEGER
  }, {
    sequelize,
    modelName: 'delivery_staffs',
  });
  return DeliveryStaff;
};