describe('Geld Overmaken naar andere rekening zonder beschrijving', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000/');
    cy.get(".login__account").first().click();
  })
  it('Geld overmaken met rekening waar geld op staat', () => {
    cy.visit('http://localhost:3000/transfer');
    cy.get('.to-account').select("Sara Ravestein");
    cy.get('#amount')
        .invoke('prop', 'validationMessage')
        .should('not.be.empty')
  });
})