@@<%@ taglib prefix="foo" tagdir="/WEB-INF/tags" %>@@
@@<foo:date>@@
  In the US today is
  @@${month}@@/@@${date}@@/@@${year}@@,
  but in Europe it is 
  @@${date}@@/@@${month}@@/@@${year}@@.
@@</foo:date>@@
