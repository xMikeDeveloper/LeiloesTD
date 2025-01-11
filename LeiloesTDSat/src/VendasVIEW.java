/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author devmy
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VendasVIEW extends JFrame {

    private JTable tabelaVendas;
    private JScrollPane scrollPane;
    private JButton btnVoltar;

    public VendasVIEW() {
        setTitle("Tela de Vendas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha apenas esta janela
        setLocationRelativeTo(null);
        setLayout(null);

        // Tabela para listar produtos vendidos
        tabelaVendas = new JTable(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Nome", "Valor", "Status"}
        ));
        scrollPane = new JScrollPane(tabelaVendas);
        scrollPane.setBounds(20, 20, 550, 250);
        add(scrollPane);

        // Botão Voltar
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(250, 300, 100, 30);
        add(btnVoltar);

        // Ação do botão Voltar
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela atual
            }
        });

        // Carrega os produtos vendidos na tabela
        listarProdutosVendidos();
    }

    private void listarProdutosVendidos() {
        try {
            ProdutosDAO produtosdao = new ProdutosDAO();
            DefaultTableModel model = (DefaultTableModel) tabelaVendas.getModel();
            model.setNumRows(0); // Limpa a tabela

            ArrayList<ProdutosDTO> produtosVendidos = produtosdao.listarProdutosVendidos();
            for (ProdutosDTO produto : produtosVendidos) {
                model.addRow(new Object[]{
                    produto.getId(),
                    produto.getNome(),
                    produto.getValor(),
                    produto.getStatus()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new VendasVIEW().setVisible(true);
    }
}
