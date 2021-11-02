package com.example.Picnic.model.enums;

import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RestMethod {

    //Cart
    getCart ("/cart", HttpMethod.GET),
    clearCart ("/cart/clear", HttpMethod.POST),
    addProductToCart ("/cart/add_product", HttpMethod.POST),
    removeProductFromCart("/cart/remove_product", HttpMethod.POST),

    //Product
    getCategories("/my_store?", HttpMethod.GET),
    getList("/lists/{list}", HttpMethod.GET),
    getSubList("/lists/{0}?sublist={1}", HttpMethod.GET),
    getProductsBySearchTerm("/search?search_term={0}", HttpMethod.GET),
    getProduct("/product/{0}", HttpMethod.GET),

    //User
    getUser("/user", HttpMethod.GET),
    loginUser("/user/login", HttpMethod.POST);

    private final String methodName;
    private final HttpMethod httpMethod;
    private final String baseUrl = "https://storefront-prod.nl.picnicinternational.com/api/17";

    RestMethod(String methodName, HttpMethod httpMethod){
        this.methodName = methodName;
        this.httpMethod = httpMethod;
    }

    /**
     * Returns the formatted link based on the argumentList.
     * Anchors in the list are replaced in order of the list.
     * No check is done if the number of arguments match the number of anchors.
     *
     * @param  argumentList  List of the arguments that should replace the anchors
     * @return  The formatted link
     */
    public String getUrl(List<String> argumentList){
        Pattern pattern = Pattern.compile("\\{[\\d|\\w]+}");
        Matcher matcher = pattern.matcher(methodName);

        int index = 0;
        int lastIndex = 0;
        StringBuilder output = new StringBuilder();
        output.append(baseUrl);
        while (matcher.find()) {
            output.append(methodName, lastIndex, matcher.start())
                    .append(argumentList.get(index));

            lastIndex = matcher.end();
            index ++;
        }

        return output.toString();
    }

    /**
     * This action should be used if the link has no anchors
     *
     * @return  The link
     */
    public String getUrl() {
        return baseUrl + methodName;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }
}
