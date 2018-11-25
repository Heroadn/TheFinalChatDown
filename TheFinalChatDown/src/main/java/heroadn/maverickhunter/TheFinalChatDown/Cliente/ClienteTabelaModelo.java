package heroadn.maverickhunter.TheFinalChatDown.Cliente;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ClienteTabelaModelo extends AbstractTableModel{

	private static final long serialVersionUID = -6753766400016347265L;
	
	private List<Cliente> linhas;	
	
	private String[] colunas = new String[]{
			"Nome","Host","ID"};
	
	public ClienteTabelaModelo() {
		linhas = new ArrayList<>();
	}
	
	@Override
	public int getRowCount() {
		return linhas.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Cliente cliente = linhas.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return cliente.getNome();
		case 1:
			return cliente.getHostName();
		case 2:
			return cliente.getId();
		default:
			throw new IndexOutOfBoundsException("Coluna out of bounds");
		}
	}
	
	@Override
	public String  getColumnName(int columnIndex) {
		return colunas[columnIndex];	
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex){
		return String.class;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Cliente cliente = linhas.get(rowIndex);
		
		switch (rowIndex) {
			case 0: 
				cliente.setNome(aValue.toString());
			case 1: 
				cliente.setHostName(aValue.toString());
			case 2: 
				cliente.setId(Integer.parseInt(aValue.toString()));
			default: //possivel erro;
		}
		fireTableCellUpdated(rowIndex, columnIndex);
    }
	
	//modifica uma linha especifica
	public void setValueAt(Cliente aValue, int rowIndex) {
		Cliente cliente = linhas.get(rowIndex);
		cliente.setNome(aValue.toString());
		cliente.setHostName(aValue.toString());
		cliente.setId(Integer.parseInt(aValue.toString()));
		
		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);
    }
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
	
	public Cliente getCliente(int index) {
		return linhas.get(index);
	}
	
	public void addCliente(Cliente cliente) {
		linhas.add(cliente);
		
		//peguando a quantidade de linhas
		int ultimoIndex = getRowCount() - 1;
		
		//Inserindo na ultima linha 
		fireTableRowsInserted(ultimoIndex, ultimoIndex);
	}
	
	public void removeCliente(int index) {
		linhas.remove(index);
		fireTableRowsDeleted(index, index);
	}
	
	public void addListaCliente(List<Cliente> cliente) {
		int tamanhoAntigo = getRowCount();
		linhas.addAll(cliente);
		
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}
	
	//Remove todos os Registros
	public void limpar() {
		linhas.clear();
		fireTableDataChanged();
	}
	
	//Verifica se nao esta vazia
	public boolean isEmpty() {
		return linhas.isEmpty();
	}
	
	
}
