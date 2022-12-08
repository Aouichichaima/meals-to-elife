'use strict';
/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface, Sequelize) {
    await queryInterface.createTable('CustomerOrders', {
      id: {
        allowNull: false,
        autoIncrement: true,
        primaryKey: true,
        type: Sequelize.INTEGER
      },
      meals: {
        type: Sequelize.JSON
      },
      ordered_at: {
        type: Sequelize.DATE,
        allowNull: false
      },
      UserId: {
        type: Sequelize.INTEGER,
        references: {
          model: { tableName: 'users'},
          key: 'id'
        },
        allowNull: false
      },
      RestaurantId: {
        type: Sequelize.INTEGER,
        references: { 
          model: { tableName: 'restaurants' },
          allowNull: false
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
    await queryInterface.dropTable('CustomerOrders');
  }
};