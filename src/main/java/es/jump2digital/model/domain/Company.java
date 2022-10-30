package es.jump2digital.model.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "companies")
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
	 * No need to create constructor and setters because I have lombok
	 * imported and installed with the annotations that we see above the class is
	 * enough
	 * 
	 * I needed to create getters for solve a difficulty with the GET endpoint: 
	 * http://localhost:8080/IndustrySizeFounded. The difficulty was that Industry 
	 * can be null and I replaced that possibility giving the value "no specified"
	 * in the getIndustry() when is null industry.
	 * 
	 * The @Document annotation refers to the collection created in Mongo, where I
	 * have the json with the companies
	 */

	public int sizeToInt() {
		int sizeInInt = 0;
		List<Integer> sizesInInt = new ArrayList<>();
		Matcher finder = Pattern.compile("\\d+").matcher(getSize());
		while (finder.find()) {
			sizesInInt.add(Integer.parseInt(finder.group()));
		}
		if (sizesInInt.size() > 1) {
			int num1 = sizesInInt.get(0);
			int num2 = sizesInInt.get(1);
			sizeInInt = num2 - num1;
		} else {
			// cause there are sizes 10001+
			sizeInInt = 10002;
		}
		return sizeInInt;

	}

	public ObjectId get_id() {
		return _id;
	}

	public String getId() {
		return id;
	}

	public String getWebsite() {
		return website;
	}

	public String getName() {
		return name;
	}

	public int getFounded() {
		return founded;
	}

	public String getSize() {
		return size;
	}

	public String getLocality() {
		return locality;
	}

	public String getRegion() {
		return region;
	}

	public String getCountry() {
		return country;
	}

	public String getIndustry() {
		if (industry == null) {
			industry = "not specified";
		}

		return industry;
	}

	public String getLinkedin_url() {
		return linkedin_url;
	}
	/*
	 * It can be done by other ways, but I prefer to use a Comparator to order the 
	 * list of Companies in the Controllers so I need this three Comparator. 
	 * The first one orders by Size, the second by Founded and the last one by 
	 * Industry
	 */

	public static Comparator<Company> SizeCompanyComparator = new Comparator<>() {
		public int compare(Company c1, Company c2) {
			int size1 = c1.sizeToInt();
			int size2 = c2.sizeToInt();
			// ascending order
			return size1 - size2;
		}
	};
	public static Comparator<Company> FoundedCompanyComparator = new Comparator<>() {
		public int compare(Company c1, Company c2) {
			int founded1 = c1.getFounded();
			int founded2 = c2.getFounded();
			// descending order
			return founded2 - founded1;
		}
	};
	public static Comparator<Company> IndustryCompanyComparator = new Comparator<>() {
		public int compare(Company c1, Company c2) {
			String industry1 = c1.getIndustry();
			String industry2 = c2.getIndustry();
			return industry1.compareTo(industry2);
		}
	};

}








//Autor: peperamos.cat
