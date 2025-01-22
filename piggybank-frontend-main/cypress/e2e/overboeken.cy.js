describe('Geld Overmaken naar andere rekening met beschrijving', () => {
  beforeEach(() => {
    cy.visit('http://localhost:3000/');
    cy.get(".login__account").first().click();
  })
  it('Geld overmaken met rekening waar geld op staat', () => {
    cy.visit('http://localhost:3000/transfer');
    cy.get("textarea").type("Dit is een overboeking");
    cy.get('.to-account').select("Sara Ravestein");
    cy.get('.amount-input').type(100);
    cy.get("[data-test-id='overboeken-button']").click();
    cy.get('.alert').should("be.visible");
  });
})