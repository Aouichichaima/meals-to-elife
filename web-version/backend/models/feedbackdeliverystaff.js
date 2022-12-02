'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class FeedbackDeliveryStaff extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      // define association here
      FeedbackDeliveryStaff.belongsTo(models.DeliveryStaff);
    }
  }
  FeedbackDeliveryStaff.init({
    rating: DataTypes.DECIMAL(2, 1),
    comment: DataTypes.STRING(999),
    DeliveryStaffId: DataTypes.INTEGER
  }, {
    sequelize,
    modelName: 'FeedbackDeliveryStaff',
  });
  return FeedbackDeliveryStaff;
};