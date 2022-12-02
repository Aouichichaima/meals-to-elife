'use strict';
/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface, Sequelize) {
    await queryInterface.createTable('Restaurants', {
      id: {
        allowNull: false,
        autoIncrement: true,
        primaryKey: true,
        type: Sequelize.INTEGER
      },
      name: {
        type: Sequelize.STRING(45)
      },
      address: {
        type: Sequelize.STRING(250)
      },
      menu: {
        type: Sequelize.JSON,
        defaultValue: '[]'
      },
      rating: {
        type: Sequelize.DECIMAL(2, 1)
      },
      UserId: {
        type: Sequelize.INTEGER,
        allowNull: false,
        references: {
          model: { tableName: 'users' },
          key: 'id'
        }
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
    await queryInterface.dropTable('Restaurants');
  }
};