

public class VagaTest {

    @Test
    void testEstacionarVagaDisponivel() {
        Vaga vaga = new Vaga(1, 1);
        assertTrue(vaga.estacionar());
    }

    @Test
    void testEstacionarVagaNaoDisponivel() {
        Vaga vaga = new Vaga(2, 2);
        vaga.setDisponivel(false);
        assertFalse(vaga.estacionar());
    }

    @Test
    void testSairVagaDisponivel() {
        Vaga vaga = new Vaga(3, 3);
        assertTrue(vaga.sair());
    }

    @Test
    void testSairVagaNaoDisponivel() {
        Vaga vaga = new Vaga(4, 4);
        vaga.setDisponivel(false);
        assertFalse(vaga.sair());
    }

    @Test
    void testDisponivelVagaDisponivel() {
        Vaga vaga = new Vaga(5, 5);
        assertTrue(vaga.disponivel());
    }

    @Test
    void testDisponivelVagaNaoDisponivel() {
        Vaga vaga = new Vaga(6, 6);
        vaga.setDisponivel(false);
        assertFalse(vaga.disponivel());
    }

    @Test
    void testCriarVaga() {
        Vaga vaga = new Vaga(7, 7);
        assertNotNull(vaga);
    }

    @Test
    void testCriarVagaId() {
        Vaga vaga = new Vaga(8, 8);
        assertEquals("16", vaga.getId());
    }

    @Test
    void testSetId() {
        Vaga vaga = new Vaga(9, 9);
        vaga.setId("10");
        assertEquals("10", vaga.getId());
    }

    @Test
    void testSetDisponivel() {
        Vaga vaga = new Vaga(10, 10);
        vaga.setDisponivel(false);
        assertFalse(vaga.isDisponivel());
    }
}
