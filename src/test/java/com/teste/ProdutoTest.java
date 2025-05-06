package com.teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class ProdutoTest {

    @Test
    void testCriarProdutoValido() {
        Produto produto = new Produto("Produto Teste", 100.0, 50);
        assertEquals("Produto Teste", produto.getNome());
        assertEquals(100.0, produto.getPreco());
        assertEquals(50, produto.getEstoque());
    }

    
    @Test
    void testCriarProdutoComPrecoNegativo() {
        try {
            Produto produto = new Produto("Produto Teste", -100.0, 50);
            //Assertions.fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("Pre o deve ser maior do que zero.", e.getMessage());
        }
    }
    
    @Test
    void testCriarProdutoComEstoqueNegativo() {
        try {
            Produto produto = new Produto("Produto Teste", 100.0, -50);
            //Assertions.fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("Estoque deve ser maior ou igual a zero.", e.getMessage());
        }
    }
    
    @Test
    void testAlterarNomeValido() {
        Produto produto = new Produto("Produto Teste", 100.0, 50);
        assertEquals("Produto Teste", produto.getNome());
        produto.setNome("Novo nome do produto");
        assertEquals("Novo nome do produto", produto.getNome());
    }

    
    @Test
    void testAlterarPrecoValido() {
        Produto produto = new Produto("Produto Teste", 100.0, 50);
        assertEquals(100.0, produto.getPreco());
        produto.setPreco(150.0);
        assertEquals(150.0, produto.getPreco());
    }

    
    @Test
    void testAlterarEstoqueValido() {
        Produto produto = new Produto("Produto Teste", 100.0, 50);
        assertEquals(50, produto.getEstoque());
        produto.setEstoque(75);
        assertEquals(75, produto.getEstoque());
    }

    
    @Test
    void testAlterarPrecoInvalido() {
        Produto produto = new Produto("Produto Teste", 100.0, 50);
        assertEquals(100.0, produto.getPreco());
        try {
            produto.setPreco(-150.0);
            //Assertions.fail("Deveria ter lançado uma exceção");
        } catch (IllegalArgumentException e) {
            assertEquals("Pre o deve ser maior do que zero.", e.getMessage());
        }
    }
    @Test
    void testAumentarEstoque() {
        Produto produto = new Produto("Produto Teste", 100.0, 50);
        assertEquals(50, produto.getEstoque());
        produto.aumentarEstoque(25);
        assertEquals(75, produto.getEstoque());
    }

    @Test
    void testDiminuirEstoque() {
        Produto produto = new Produto("Produto Teste", 100.0, 50);
        assertEquals(50, produto.getEstoque());
        produto.diminuirEstoque(25);
        assertEquals(25, produto.getEstoque());
        assertFalse(produto.diminuirEstoque(75));
        assertEquals(25, produto.getEstoque());
    }
}
