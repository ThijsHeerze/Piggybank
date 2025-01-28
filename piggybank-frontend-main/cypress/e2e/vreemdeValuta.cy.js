describe('Geld overmaken met vreemde valuta', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000/');
    cy.get(".login__account").first().click();
  });

  it('Foutmelding tonen bij vreemde valuta', () => {
    cy.visit('http://localhost:3000/transfer');
    cy.get("textarea").type("Vreemde valuta testen");
    cy.get('.to-account').select("Sara Ravestein");
    cy.get('.amount-input').type('100 USD');
    cy.get("[data-test-id='overboeken-button']").click();


  });
});
