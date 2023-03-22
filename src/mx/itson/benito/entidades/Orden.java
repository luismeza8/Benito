package mx.itson.benito.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import mx.itson.benito.enumeradores.EstadoEnum;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * La convinacion de pedidos
 * @author lm
 */
@Entity
public class Orden {

    /**
     * @param estado the estado to set
     */
    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
    }

    /**
     * @return the estadoComentario
     */
    public String getEstadoComentario() {
        return estadoComentario;
    }

    /**
     * @param estadoComentario the estadoComentario to set
     */
    public void setEstadoComentario(String estadoComentario) {
        this.estadoComentario = estadoComentario;
    }

    /**
     * @return the fechaEstado
     */
    public Date getFechaEstado() {
        return fechaEstado;
    }

    /**
     * @param fechaEstado the fechaEstado to set
     */
    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos;
    
    private EstadoEnum estado;
    private String estadoComentario;
    
    @Temporal(TemporalType.DATE)
    private Date fechaEstado;
    
    private double total;
    private double subtotal;
    private String comentario;
    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    private String folio;    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public EstadoEnum getEstado() {
        return estado;
    }

}
