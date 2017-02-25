
package br.cesjf.lppo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "NovoRegistroServlet", urlPatterns = {"/novo.html"})
public class NovoRegistroServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/novo-produto.jsp").forward(request, response);
       
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome=request.getParameter("nome");
        Integer quantidade=Integer.valueOf(request.getParameter("qtde"));
        Logger.getLogger(NovoRegistroServlet.class.getName()).log(Level.INFO, "POST: "+nome+""+quantidade);
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
             String url = "jdbc:derby://localhost:1527//lppo-2017-1";  
             Connection conexao = DriverManager.getConnection(url, "usuario", "senha");
             System.out.println("Conexao aberta com sucesso");
             
             String sql = " INSERT INTO produto (nome, qtd) VALUES ('"
                     +nome + "' , "
                     +quantidade+ ")";
             Statement operacao = conexao.createStatement();
             operacao.executeUpdate(sql);
           
             
        } catch (Exception e) {
            
            Logger.getLogger(NovoRegistroServlet.class.getName()).log(Level.SEVERE, "Erro ao inserir o Registro" +e);
        }
            response.sendRedirect("lista.html");
        
    }

}
