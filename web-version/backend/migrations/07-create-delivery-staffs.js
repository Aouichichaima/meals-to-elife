'use strict';
/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface, Sequelize) {
    await queryInterface.createTable('delivery_staffs', {
      id: {
        allowNull: false,
        autoIncrement: true,
        primaryKey: true,
        type: Sequelize.INTEGER
      },
      cin: {
        type: Sequelize.STRING(8)
      },
      phone: {
        type: Sequelize.STRING(30)
      },
      image_path: {
        type: Sequelize.STRING(999)
      },
      first_name: {
        type: Sequelize.STRING(45)
      },
      last_name: {
        type: Sequelize.STRING(45)
      },
      RestaurantId: {
        type: Sequelize.INTEGER,
        references: {
          model: {tableName: 'restaurants'},
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
    await queryInterface.dropTable('delivery_staffs');
  }
};