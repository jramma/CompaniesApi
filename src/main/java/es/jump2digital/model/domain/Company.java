package es.jump2digital.model.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @Document(collection = "companies")
public class Company {

	@Id
	private ObjectId _id;
	private String id;
	private String website;
	private String name;
	private int founded;
	private String size;
	private String locality;
	private String region;
	private String country;
	private String industry;
	private String linkedin_url;

	/*
	 * No need to create constructor, getters and setters because I have lombok
	 * imported and installed with the annotations that we see above the class is
	 * enough
	 * 
	 * The Document annotation refers to the collection created in mongo where I
	 * have the json with the companies
	 */

	

	

	public int sizeToInt() {

		String num1 = "", num2 = "";
		boolean founded = false;
		for (int i = 0; i < getSize().length(); i++) {
			if (getSize().charAt(i) != '-' && !founded) {
				num1 = num1 + getSize().charAt(i);
			} else {
				founded = true;
				if (getSize().charAt(i) != '-') {
					num2 = num2 + getSize().charAt(i);
				}
			}
		}
		int in1 = Integer.parseInt(num1);
		int in2 = Integer.parseInt(num2);

		return in1 + in2;

	}

}
