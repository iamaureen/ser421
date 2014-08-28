import "xml.q"

import_dtd
  (prefix = "Html_" namespace = "http://www.w3.org/1999/xhtml")
  "xhtml1-transitional.dtd"

namespace = "http://www.w3.org/1999/xhtml"

namespace b = "http://businesscard.org"

type Cardlist = b:cardlist[Title2,Card*]
type Card = b:card[Name,Title,Email,Phone?,Logo?]
type Name = b:name[String]
type Title = b:title[String]
type Email = b:email[String]
type Phone = b:phone[String]
type Logo = b:logo[@uri[String]]
type Title2 = b:title[(Html_h1|Html_em|String)*]

let val cl = validate load_xml("http://www.brics.dk/ixwt/examples/cardlist.xml") with Cardlist

fun convertList(val cl as Cardlist) : Html_html =
  match cl with
    b:cardlist[b:title[val t], val cards] 
      -> htlm[body[t,convertCards(cards)]]

fun convertCards(val cs as Card* ) : Html_div* =
  match cs with
    b:card[b:name[val n],b:titel[val t],b:email[val e],Any],val rest
      -> divv[h2[n],t,br[],"Email: " ^ e],convertCards(rest)
  | () -> ()

let val _ = save_xml("cardlist.html")(convertList(cl))
