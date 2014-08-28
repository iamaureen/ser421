<%@ taglib prefix="poll" tagdir="/WEB-INF/tags/poll" %>
<poll:quickpoll title="Quick Poll" 
                duration="3600" 
                sheet="http://www.quickpolls.foo/fancy.css">
  <poll:setup>
    The question has been set to "${question}".
  </poll:setup>
  <poll:ask>
    <center>
      ${question}?
    </center>
    <button type="submit" name="vote" value="yes">
      <img src="http://www.quickpolls.foo/yes.gif">
    </button>
    <button type="submit" name="vote" value="no">
      <img src="http://www.quickpolls.foo/no.gif">
    </button>
  </poll:ask>
  <poll:vote>
    You have voted ${vote}.
  </poll:vote>
  <poll:results>
    <table border="0">
      <tr>
        <td>Yes:</td>
        <td>
          <table>
            <tr>
              <td bgcolor=black height=20 width="${(300*yes)/total}">
            </tr>
          </table>
        </td>
        <td>${yes}</td>
      </tr>
      <tr>
        <td>No:</td>
          <table>
            <tr>
              <td bgcolor=black height=20 width="${(300*no)/total}">
            </tr>
          </table>
        <td>${no}</td>
      </tr>
    </table>
  </poll:results>
  <poll:timeout>
    Sorry, the polls have closed.
  </poll:timeout>
</poll:quickpoll>
