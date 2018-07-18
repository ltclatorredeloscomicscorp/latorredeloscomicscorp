package models;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Product {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Long id;
	@Persistent private String name;
	@Persistent private double amount;
	@Persistent private String description;
	@Persistent private String author;
	@Persistent String dateOfSale;
	@Persistent String image;
	
	
	/**
	 * @param id
	 * @param name
	 * @param amount
	 * @param description
	 * @param author
	 * @param dateOfSale
	 * @param image
	 */
	public Product(String name, double amount, String description, String author,String dateOfSale,String image) {
		super();
		this.name = name;
		this.amount = amount;
		this.description = description;
		this.author = author;
		this.dateOfSale = dateOfSale;
		this.image=image;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getDateOfSale() {
		return this.dateOfSale;
	}
	public void setDateOfSale(String dateOfSale) {
		this.dateOfSale = dateOfSale;
	}
}
