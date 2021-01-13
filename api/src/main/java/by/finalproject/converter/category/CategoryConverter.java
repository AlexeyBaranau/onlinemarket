//package by.finalproject.converter.category;
//
//
//import by.finalproject.controller.request.category.CategoryCreate;
//import by.finalproject.domain.Category;
//import org.springframework.core.convert.converter.Converter;
//
//public abstract class CategoryConverter<S, T> implements Converter<S, T> {
//
//    protected Category doConvert (Category category, CategoryCreate request){
//        category.setName(request.getName());
//        return category;
//    }
//}
