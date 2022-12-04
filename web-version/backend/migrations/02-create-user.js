'use strict';
/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface, Sequelize) {
    await queryInterface.createTable('Users', {
      id: {
        allowNull: false,
        autoIncrement: true,
        primaryKey: true,
        type: Sequelize.INTEGER
      },
      cin: {
        type: Sequelize.STRING(8),
        allowNull: false,
        unique: true
      },
      first_name: {
        type: Sequelize.STRING(45),
        allowNull: false
      },
      last_name: {
        type: Sequelize.STRING(45),
        allowNull: false
      },
      phone: {
        type: Sequelize.STRING(30),
        allowNull: false
      },
      email: {
        type: Sequelize.STRING(60),
        unique: true
      },
      password: {
        type: Sequelize.STRING(999),
        allowNull: false
      },
      image_path: {
        type: Sequelize.STRING(999),
        defaultValue: null
      },
      isAuthorized: {
        type: Sequelize.BOOLEAN,
        defaultValue: false,
        allowNull: false
      },
      RoleId: {
        type: Sequelize.INTEGER,
        references: {
          model: { tableName: 'roles' },
          key: 'id'
        },
        allowNull: false
      },
      createdAt: {
        allowNull: false,
        type: Sequelize.DATE
      },
      updatedAt: {
        allowNull: false,
        type: Sequelize.DATE
      }
    });
  },
  async down(queryInterface, Sequelize) {
    await queryInterface.dropTable('Users');
  }
};