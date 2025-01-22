describe('Login logout', () => {
  it('passes', () => {
      cy.visit('http://localhost:3000/');
      cy.get(".login__account").first().click();
      cy.get(".app__logout").first().click();

  })
})