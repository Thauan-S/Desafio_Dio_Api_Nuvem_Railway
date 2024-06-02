package com.api.unittests.mapper.mocks;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.api.curso.data.vo.v1.LivroVO;
import com.api.curso.model.Livro;

public class MockLivro {


    public Livro mockEntity() {
        return mockEntity(0);
    }
    
    public LivroVO mockVO() {
        return mockVO(0);
    }
    
    public List<Livro> mockEntityList() {
        List<Livro> livros = new ArrayList<Livro>();
        for (int i = 0; i < 14; i++) {
            livros.add(mockEntity(i));
        }
        return livros;
    }

    public List<LivroVO> mockVOList() {
        List<LivroVO> livros = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            livros.add(mockVO(i));
        }
        return livros;
    }
    
    public Livro mockEntity(Integer number) {
        Livro livro = new Livro();
        livro.setAutor("autor Teste" + number);
        livro.setPreco( 200D);
        livro.setTitulo("Titutlo teste"+ number);
        livro.setId(number.longValue());
        livro.setDataLancamento(new Date(2024-23-01));
        return livro;
    }

    public LivroVO mockVO(Integer number) {
        LivroVO livro = new LivroVO();
        livro.setAutor("autor Teste" + number);
        livro.setPreco( 200D);
        livro.setTitulo("Titutlo teste"+ number);
        livro.setKey(number.longValue());
        livro.setDataLancamento(new Date(2024-23-01));
        return livro;
    }

}
