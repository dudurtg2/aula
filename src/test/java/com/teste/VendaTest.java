package com.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VendaTest {
    @Test
    void testRealizarVenda() {
        Produto produto = new Produto("Produto Teste", 100.0, 20);
        Venda venda = new Venda(produto, 10);
        assertTrue(venda.realizarVenda());
        assertEquals(10, produto.getEstoque());
        assertEquals(1000.0, venda.getTotalVenda());
    }

    @Test
    void testRealizarVendaComQuantidadeIgualEstoque() {
        Produto produto = new Produto("Produto Teste", 100.0, 10);
        Venda venda = new Venda(produto, 10);
        assertTrue(venda.realizarVenda());
        assertEquals(0, produto.getEstoque());
        assertEquals(1000.0, venda.getTotalVenda());
    }

    @Test
    void testRealizarVendaComQuantidadeMaiorQueEstoque() {
        Produto produto = new Produto("Produto Teste", 100.0, 5);
        Venda venda = new Venda(produto, 10);
        try {
            assertFalse(venda.realizarVenda());
            //Assertions.fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("Estoque insuficiente.", e.getMessage());
        }
    }

    @Test
    void testTotalVenda() {
        Produto produto = new Produto("Produto Teste", 100.0, 5);
        Venda venda = new Venda(produto, 2);
        assertNotEquals(200.0, venda.getTotalVenda());
    }

    @Test
    void testAumentarEstoqueAposVenda() {
        Produto produto = new Produto("Produto Teste", 100.0, 5);
        Venda venda = new Venda(produto, 2);
        assertTrue(venda.realizarVenda());
        assertEquals(3, produto.getEstoque());
        produto.aumentarEstoque(10);
        assertEquals(13, produto.getEstoque());
    }

    @Test
    void testDiminuirEstoqueAposVenda() {
        Produto produto = new Produto("Produto Teste", 100.0, 10);
        Venda venda = new Venda(produto, 4);
        assertTrue(venda.realizarVenda());
        assertEquals(6, produto.getEstoque());
        produto.diminuirEstoque(4);
        assertEquals(2, produto.getEstoque());
    }

    @Test
    void testRealizarVendaComProdutoNulo() {
        Venda venda = new Venda(null, 10);
        try {
            assertTrue(venda.realizarVenda());
            Assertions.fail("Deveria ter lançado uma exceção");
        } catch (NullPointerException e) {
            assertNotEquals("Produto nulo.", e.getMessage());
        }
    }

    @Test
    void testCriarVendaComQuantidadeNegativa() {
        Produto produto = new Produto("Produto Teste", 100.0, 10);
        Venda venda = new Venda(produto, -5);
        try {
            assertTrue(venda.realizarVenda());
            //Assertions.fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("Quantidade de vendas deve ser maior do que zero.", e.getMessage());
        }
    }

    @Test
    void testTentativaDeVendaComEstoqueInsuficienteAlterarEstoque() {
        Produto produto = new Produto("Produto Teste", 100.0, 5);
        Venda venda = new Venda(produto, 10);

        assertTrue(!venda.realizarVenda());
        produto.aumentarEstoque(10);
        assertTrue(venda.realizarVenda());
        assertEquals(5, produto.getEstoque());
    }

    @Test
    void testVendaComVariosProdutos() {
        Produto produto1 = new Produto("Produto 1", 100.0, 5);
        Produto produto2 = new Produto("Produto 2", 200.0, 10);
        Venda venda1 = new Venda(produto1, 2);
        Venda venda2 = new Venda(produto2, 4);

        assertTrue(venda1.realizarVenda());
        assertEquals(3, produto1.getEstoque());
        assertTrue(venda2.realizarVenda());
        assertEquals(6, produto2.getEstoque());
    }

    @Test
    void testAlterarPrecoDeProdutoAntesDeVenda() {
        Produto produto = new Produto("Produto Teste", 100.0, 5);
        Venda venda = new Venda(produto, 2);

        produto.setPreco(150.0);
        assertTrue(venda.realizarVenda());
        assertEquals(300.0, venda.getTotalVenda());
    }

    @Test
    void testVendaComEstoqueInicialZero() {
        Produto produto = new Produto("Produto Teste", 100.0, 0);
        Venda venda = new Venda(produto, 1);
        try {
            assertFalse(venda.realizarVenda());
            //Assertions.fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("Estoque insuficiente.", e.getMessage());
        }
    }

    @Test
    void testVendaComAumentoDeEstoque() {
        Produto produto = new Produto("Produto Teste", 100.0, 0);
        Venda venda = new Venda(produto, 1);
        try {
            assertFalse(venda.realizarVenda());
            //Assertions.fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("Estoque insuficiente.", e.getMessage());
        }
        produto.aumentarEstoque(10);
        assertTrue(venda.realizarVenda());
        assertNotEquals(1, produto.getEstoque());
    }

}
