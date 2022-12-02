'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class User extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      // define association here
      User.belongsTo(models.Role);
      User.hasMany(models.Restaurant);
    }
  }
  User.init({
    cin: {
      type: DataTypes.STRING(8),
      validate: {
        len: {
          args: [8, 8],
          msg: 'Please provide a valid cin with 8 digit'
        },

      }
    },
    first_name: DataTypes.STRING(45),
    last_name: DataTypes.STRING(45),
    phone: DataTypes.STRING(30),
    email: {type: DataTypes.STRING(60), validate: {
      isEmail: {args: true, msg: 'invalid mail'}
    }},
    password: DataTypes.STRING(999),
    image_path: DataTypes.STRING(999),
    isAuthorized: DataTypes.BOOLEAN,
    RoleId: DataTypes.INTEGER
  }, {
    sequelize,
    modelName: 'User',
  });
  return User;
};