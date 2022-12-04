'use strict';

const { faker } = require('@faker-js/faker');
const isPrime = require('../util/isPrime.js');

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up (queryInterface, Sequelize) {
    const users = generateFakeUsers(250_000);
    await queryInterface.bulkInsert('users', users, {});
    
  },

  async down (queryInterface, Sequelize) {
    /**
     * Add commands to revert seed here.
     *
     * Example:
     * await queryInterface.bulkDelete('People', null, {});
     */
  }
};



function generateFakeUsers(rowsCount) {
  const users = [
    {
      cin: '14253647',
      first_name: 'asma',
      last_name: 'chebbi',
      phone: faker.phone.number('+216 ## ### ###'),
      email: 'asma.chebbi@fondation-tunisie.org',
      password: faker.internet.password(),
      image_path: faker.internet.avatar(),
      isAuthorized: 1,
      RoleId: 1,
      createdAt: faker.date.past(12),
      updatedAt: faker.date.recent()
    },
    {
      cin: '14253648',
      first_name: 'mariem',
      last_name: 'm.',
      phone: faker.phone.number('+216 ## ### ###'),
      email: 'mariem@fondation-tunisie.org',
      password: faker.internet.password(),
      image_path: faker.internet.avatar(),
      isAuthorized: 1,
      RoleId: 1,
      createdAt: faker.date.past(12),
      updatedAt: faker.date.recent()
    },
  ];

  const insertedEmails = {};
  const insertedCin = {};
  
  const email = (first_name, last_name) => {
    let condidate = faker.internet.email(first_name, last_name);

    while(insertedEmails[condidate]) {
      condidate = faker.internet.email(first_name, last_name);
    }

    insertedEmails[condidate] = true;
    return condidate;
  }

  const cin = () => {
    let condidate = faker.random.numeric(8);
    while(insertedCin[condidate])
      condidate = faker.random.numeric(8);
    
    insertedCin[condidate] = true;
    return condidate;
  }

  for(let i = 3; i <= rowsCount; i++) {
    const first_name = faker.name.firstName(), last_name = faker.name.lastName();
    const newUser = {
      cin: cin(),
      first_name,
      last_name,
      phone: faker.phone.number('+216 ## ### ###'),
      email: email(first_name, last_name),
      password: faker.internet.password(),
      image_path: faker.internet.avatar(),
      isAuthorized: Math.random() > 0.5 ? 1 : 0,
      RoleId: i <= 100 && isPrime(i) ? 2 : 3,
      createdAt: faker.date.past(12),
      updatedAt: faker.date.recent()
    }
    users.push(newUser);
  }

  return users;
}