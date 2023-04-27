
public class Menu {
	private String KodeMenu;
	private String NamaMenu;
	private String HargaMenu;
	private String StokMenu;
	public Menu(String KodeMenu, String NamaMenu, String harga, String stok) {
		this.KodeMenu = KodeMenu;
		this.NamaMenu = NamaMenu;
		this.HargaMenu = harga;
		this.StokMenu = stok;
	}
	public String getKodeMenu() {
		return KodeMenu;
	}
	public void setKodeMenu(String kodeMenu) {
		KodeMenu = kodeMenu;
	}
	public String getNamaMenu() {
		return NamaMenu;
	}
	public void setNamaMenu(String namaMenu) {
		NamaMenu = namaMenu;
	}
	public String getHargaMenu() {
		return HargaMenu;
	}
	public void setHargaMenu(String hargaMenu) {
		HargaMenu = hargaMenu;
	}
	public String getStokMenu() {
		return StokMenu;
	}
	public void setStokMenu(String stokMenu) {
		StokMenu = stokMenu;
	}

}
