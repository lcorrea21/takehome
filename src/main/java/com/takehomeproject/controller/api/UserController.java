package com.takehomeproject.controller.api;

import com.takehomeproject.dao.JwtUserDetailsDAO;
import com.takehomeproject.dao.UserDAO;
import com.takehomeproject.exception.ResourceNotFoundException;
import com.takehomeproject.model.User;
import com.takehomeproject.service.UserService;
import com.takehomeproject.utils.MyLogger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/takehomeproject/users")
@SecurityRequirement(name = "javainuseapi")
public class UserController {

  private static final Logger logger = Logger.getLogger(MyLogger.class.getName());

  @Autowired private UserDAO userDAO;
  @Autowired private UserService userService;
  @Autowired private JwtUserDetailsDAO userDetailsService;

  @Operation(summary = "List users")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully Obtained Users",
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "204",
            description = "User list not found",
            content = @Content),
        @ApiResponse(
            responseCode = "401",
            description = "You are not authorized to view the user list",
            content = @Content),
        @ApiResponse(
            responseCode = "403",
            description = "You are trying to access a user that is not allowed",
            content = @Content)
      })

  // Return list of all users
  @GetMapping(value = "ListUsers")
  public ResponseEntity<List<User>> listUsers() {
    List<User> listaUsers = userDAO.findAll();
    if (listaUsers.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(listaUsers);
  }

  @GetMapping("get/{usuarioId}")
  public ResponseEntity<User> listarUsuarioId(@PathVariable("usuarioId") Long usuarioId) {
    User user =
        userDAO
            .findById(usuarioId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found, id: " + usuarioId));
    return ResponseEntity.ok(user);
  }

  // Take input as email and return appropiate status
  @GetMapping("isUserExists/{email}")
  public ResponseEntity<User> isUserExists(@PathVariable("email") String emailaddress) {
    User user =
        userDAO
            .findByEmail(emailaddress)
            .orElseThrow(
                () -> new ResourceNotFoundException("User not found, email: " + emailaddress));
    return ResponseEntity.ok(user);
  }

  @PostMapping("save")
  public ResponseEntity<?> guardarUsuario(@RequestBody User user) {
    System.out.println("Request usuario: " + user);
    User nuevoUser = userService.save(user);
    return ResponseEntity.ok(nuevoUser);
  }

  @DeleteMapping("delete/{usuarioId}")
  public ResponseEntity<Void> eliminarUsuario(@PathVariable("usuarioId") Long usuarioId) {
    User user =
        userDAO
            .findById(usuarioId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found, id: " + usuarioId));

    userDAO.deleteById(user.getId());
    return ResponseEntity.ok(null);
  }

  // Update user if exist else create a new one
  @PutMapping("UpsertUser")
  public ResponseEntity<User> UpsertUser(@RequestBody User user) {
    userService.update(user);
    return ResponseEntity.ok(user);
  }
}
