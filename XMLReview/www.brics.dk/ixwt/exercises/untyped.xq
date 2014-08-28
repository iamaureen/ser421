declare namespace rcp = "http://www.brics.dk/ixwt/recipes";

declare function local:foo($t, $r) {
  for $x in fn:doc("recipes.xml")//rcp:recipe 
  let $y := $r/following-sibling::*[1]
  where ($x is $r) and ($r/rcp:title eq $t)
  return $y/rcp:date
}

