import "xml.q"

namespace rcp = "http://www.brics.dk/ixwt/recipes"

type Collection = rcp:collection[Description,Recipe*]
type Description = rcp:description[String]
type Recipe = rcp:recipe[@id[String]?,
                         Title,
                         Date,
                         Ingredient*,
                         Preparation,
                         Comment?,
                         Nutrition,
                         Related*]
type Title = rcp:title[String]
type Date = rcp:date[String]
type Ingredient = rcp:ingredient[@name[String],
                                 @amount[String]?,
                                 @unit[String]?,
                                 (Ingredient*,Preparation)?]
type Preparation = rcp:preparation[Step*]
type Step = rcp:step[String]
type Comment = rcp:comment[String]
type Nutrition = rcp:nutrition[@calories[String],
                               @carbohydrates[String],
                               @fat[String],
                               @protein[String],
                               @alcohol[String]?]
type Related = rcp:related[@ref[String],String]

let val collection = validate load_xml("recipes.xml") with Collection

type NutritionTable = nutrition[Dish*]
type Dish = dish[@name[String],
                 @calories[String],
                 @fat[String],
                 @carbohydrates[String],
                 @protein[String],
                 @alcohol[String]]

fun extractCollection(val c as Collection) : NutritionTable =
  match c with
    rcp:collection[Description, val rs] 
      -> nutrition[extractRecipes(rs)]

fun extractRecipes(val rs as Recipe* ) : Dish* =
  match rs with
    rcp:recipe[@..,
               rcp:title[val t],
               Date,
               Ingredient*,
               Preparation,
               Comment?,
               val n as Nutrition,
               Related*], val rest
    -> extractNutrition(t,n), extractRecipes(rest)
  | () -> ()

fun extractNutrition(val t as String, val n as Nutrition) : Dish =
  match n with
    rcp:nutrition[@calories[val calories],
                  @carbohydrates[val carbohydrates],
                  @fat[val fat],
                  @protein[val protein],
                  @alcohol[val alcohol]]
    -> dish[@name[t],
            @calories[calories],
            @carbohydrates[carbohydrates],
            @fat[fat],
            @protein[protein],
            @alcohol[alcohol]]
  | rcp:nutrition[@calories[val calories],
                  @carbohydrates[val carbohydrates],
                  @fat[val fat],
                  @protein[val protein]]
    -> dish[@name[t],
            @calories[calories],
            @carbohydrates[carbohydrates],
            @fat[fat],
            @protein[protein],
            @alcohol["0%"]]
