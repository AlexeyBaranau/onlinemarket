//package by.finalproject.converter.category;
//
//import by.finalproject.controller.request.category.CategoryChange;
//import by.finalproject.domain.Category;
//import by.finalproject.exception.EntityNotFoundException;
//import by.finalproject.repository.CategoryRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class CategoryEditRequestConverter extends CategoryConverter<CategoryChange, Category>{
//    public final CategoryRepository categoryRepository;
//
//    @Override
//    public Category convert(CategoryChange request) {
//        Category category =
//                categoryRepository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);
//        return doConvert(category, request);
//    }
//}
