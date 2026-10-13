import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


public class Listasejercicio extends JFrame {
    

    private ArrayList<String> misTareas;
    

    private JTextField txtTarea;
    private JTextArea txtAreaLista;

    public Listasejercicio() {
        // Configuración básica de la ventana
        setTitle("Mi Lista de Tareas - Estudiante");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        misTareas = new ArrayList<>();

        
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // Panel superior para ingresar datos
        JPanel panelSuperior = new JPanel();
        panelSuperior.add(new JLabel("Tarea:"));
        
        txtTarea = new JTextField(15);
        panelSuperior.add(txtTarea);

        JButton btnAgregar = new JButton("Agregar");
        panelSuperior.add(btnAgregar);

        add(panelSuperior, BorderLayout.NORTH);

        
        txtAreaLista = new JTextArea();
        txtAreaLista.setEditable(false);
        txtAreaLista.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(txtAreaLista), BorderLayout.CENTER);

        
        JPanel panelInferior = new JPanel();
        JButton btnEliminar = new JButton("Eliminar última");
        JButton btnLimpiar = new JButton("Limpiar todo");
        panelInferior.add(btnEliminar);
        panelInferior.add(btnLimpiar);

        add(panelInferior, BorderLayout.SOUTH);

        
        btnAgregar.addActionListener(e -> agregarTarea());
        btnEliminar.addActionListener(e -> eliminarUltima());
        btnLimpiar.addActionListener(e -> limpiarLista());
        
        actualizarVista();
    }

    private void agregarTarea() {
        String tarea = txtTarea.getText().trim();
        
        if (!tarea.isEmpty()) {
            
            misTareas.add(tarea);
            txtTarea.setText(""); 
            actualizarVista();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor escribe una tarea.");
        }
    }

    private void eliminarUltima() {
        if (!misTareas.isEmpty()) {
            
            int ultimaPosicion = misTareas.size() - 1;
            String eliminada = misTareas.remove(ultimaPosicion);
            JOptionPane.showMessageDialog(this, "Se eliminó: " + eliminada);
            actualizarVista();
        } else {
            JOptionPane.showMessageDialog(this, "La lista ya está vacía.");
        }
    }

    private void limpiarLista() {
        
        misTareas.clear();
        actualizarVista();
    }

    private void actualizarVista() {
        txtAreaLista.setText("=== LISTA DE TAREAS ACTUALES ===\n\n");
        
        if (misTareas.isEmpty()) {
            txtAreaLista.append("(No hay elementos en la lista)\n");
        } else {
            
            for (int i = 0; i < misTareas.size(); i++) {
                txtAreaLista.append((i + 1) + ". " + misTareas.get(i) + "\n");
            }
        }
        
        txtAreaLista.append("\nTamaño actual de la lista: " + misTareas.size());
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Listasejercicio().setVisible(true);
        });
    }
}