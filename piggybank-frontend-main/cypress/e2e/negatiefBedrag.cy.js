describe('Negatief bedrag overmaken', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000/');
    cy.get(".login__account").first().click();
  })

  it('Foutmelding tonen bij negatief bedrag', () => {
    cy.visit('http://localhost:3000/transfer');
    cy.get("textarea").type("Negatief bedrag testen");
    cy.get('.to-account').select("Sara Ravestein");
    cy.get('.amount-input').type('-50');
    cy.get("[data-test-id='overboeken-button']").click();
    cy.get('#amount')
        .invoke('prop', 'validationMessage')
        .should('not.be.empty')
  });
})
