package com.web2team.restful.controller;

import com.web2team.graphql.model.TextEditor;
import com.web2team.graphql.repository.TextEditorRepository;
import com.web2team.restful.model.TextEditorBody;
import org.springframework.web.bind.annotation.*;

@RestController
public class TextEditorController {
  private final TextEditorRepository textEditorRepository;

  public TextEditorController(TextEditorRepository textEditorRepository) {
    this.textEditorRepository = textEditorRepository;
  }

  @RequestMapping(value = "/texteditor/{id}", method = RequestMethod.GET)
  public String getTextEditorData(@PathVariable Long id) {
    return textEditorRepository.findById(id).get().getContents();
  }

  @PostMapping("/texteditor")
  public boolean saveTextEditorData(@RequestBody() TextEditorBody body) {
    TextEditor textEditor = new TextEditor();
    textEditor.setId(body.getId());
    textEditor.setContents(body.getContents());

    textEditorRepository.save(textEditor);

    return true;
  }
}
