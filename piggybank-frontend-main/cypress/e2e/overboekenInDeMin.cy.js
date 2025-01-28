describe('Geld overmaken terwijl je in de min staat', () => {
  beforeEach(() => {
    // Startpagina van de applicatie bezoeken
    cy.visit('http://localhost:3000/');
    // Inloggen door de eerste account in de lijst te selecteren
    cy.get(".login__account").first().click();
  });

  it('Foutmelding tonen bij overboeken terwijl je in de min staat', () => {
    // Ga naar de pagina voor overboekingen
    cy.visit('http://localhost:3000/transfer');

    // Beschrijving toevoegen aan de transactie
    cy.get("textarea").type("Overboeken terwijl in de min");

    // Een ontvanger selecteren
    cy.get('.to-account').select("Sara Ravestein");

    // Bedrag invoeren dat het saldo verder de min in duwt
    cy.get('.amount-input').type('200');

    // Klik op de overboekknop
    cy.get("[data-test-id='overboeken-button']").click();


  });
});
