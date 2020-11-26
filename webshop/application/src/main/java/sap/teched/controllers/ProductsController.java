package sap.teched.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.connectivity.HttpDestination;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.Product;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductDescription;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.productmaster.ProductValuation;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultProductMasterService;

import sap.teched.models.WebShopProduct;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<WebShopProduct[]> getProducts() {
		WebShopProduct[] products = { new WebShopProduct("DUMMY", "Dummy Product", new BigDecimal(0)) };
		return ResponseEntity.ok(products);
	}

	private List<WebShopProduct> transformProducts(List<Product> s4Products, String shopLanguage) {
		List<WebShopProduct> products = new ArrayList<WebShopProduct>();

		for (Product s4product : s4Products) {
			String id = s4product.getProduct();
			String description = "";
			List<ProductDescription> s4descriptions = s4product.getDescriptionIfPresent().getOrElse(new ArrayList<>());
			for (ProductDescription s4description : s4descriptions) {
				if (shopLanguage.equals(s4description.getLanguage())) {
					description = s4description.getProductDescription();
					break;
				}
			}

			BigDecimal price = new BigDecimal(0);
			List<ProductValuation> valuations = s4product.getValuationIfPresent().getOrElse(new ArrayList<>());
			if (!valuations.isEmpty()) {
				price = valuations.get(0).getStandardPrice();
			}
			WebShopProduct product = new WebShopProduct(id, description, price);
			products.add(product);
		}

		return products;
	}
}
