import 'dart:convert';

class AuthDTO {
  String authorization;

  AuthDTO({
  this.authorization = "",
  });

  AuthDTO.fromJson(Map<String, dynamic>  map) :
        authorization = map['Authorization']  ?? "";

  Map<String, dynamic> toJson() => {
        'Authorization': authorization,
      };

  AuthDTO copyWith({
    String authorization,
  }) {
    return AuthDTO(
      authorization: authorization ?? this.authorization,
    );
  }
}

