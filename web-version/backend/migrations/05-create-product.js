'use strict';
/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface, Sequelize) {
    await queryInterface.createTable('Products', {
      id: {
        allowNull: false,
        autoIncrement: true,
        primaryKey: true,
        type: Sequelize.INTEGER
      },
      name: {
        type: Sequelize.STRING(99)
      },
      price: {
        type: Sequelize.DECIMAL(13, 3)
      },
      quantity: {
        type: Sequelize.INTEGER
      },
      description: {
        type: Sequelize.STRING(999)
      },
      stockId: {
        type: Sequelize.INTEGER,
        references: {
          model: { tableName: 'stocks'},
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
    await queryInterface.dropTable('Products');
  }
};